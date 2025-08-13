package com.example.lms.repository;

import com.example.lms.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for Enrollment entity with advanced SQL queries
 * utilizing complex joins and window functions for student-course allocation management.
 */
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    /**
     * Allocates students to a course based on registration order (first-come, first-served) 
     * up to the given course capacity.
     * This uses a JOIN and ORDER BY for allocation logic.
     */
    @Query(
        value = """
            SELECT e.*
            FROM enrollment e
            JOIN course c ON e.course_id = c.id
            WHERE c.id = :courseId
            ORDER BY e.enrollment_date ASC
            LIMIT :courseCapacity
        """,
        nativeQuery = true
    )
    List<Enrollment> findAllocatedEnrollments(@Param("courseId") Long courseId, @Param("courseCapacity") int courseCapacity);

    /**
     * Fetches each student's latest enrollment using a window function (ROW_NUMBER()).
     * This is useful for reporting or analyzing the most recent course taken by each user.
     */
    @Query(
        value = """
            SELECT *
            FROM (
                SELECT e.*, ROW_NUMBER() OVER (PARTITION BY e.user_id ORDER BY e.enrollment_date DESC) as rn
                FROM enrollment e
            ) ranked
            WHERE rn = 1
        """,
        nativeQuery = true
    )
    List<Enrollment> findLatestEnrollmentPerStudent();

    /**
     * Example: Find students enrolled in more than one course (using GROUP BY + HAVING).
     * Useful for identifying highly engaged students.
     */
    @Query(
        value = """
            SELECT user_id
            FROM enrollment
            GROUP BY user_id
            HAVING COUNT(DISTINCT course_id) > 1
        """,
        nativeQuery = true
    )
    List<Long> findStudentsWithMultipleCourses();

    /**
     * Example: Use window function (DENSE_RANK) to rank students within each course by enrollment date.
     * This can help with fair allocation or recognizing early adopters.
     */
    @Query(
        value = """
            SELECT e.*, DENSE_RANK() OVER (PARTITION BY e.course_id ORDER BY e.enrollment_date ASC) as course_rank
            FROM enrollment e
            WHERE e.course_id = :courseId
        """,
        nativeQuery = true
    )
    List<Object[]> rankStudentsInCourse(@Param("courseId") Long courseId);
}
