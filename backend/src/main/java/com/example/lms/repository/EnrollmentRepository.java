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

    @Query(
        value = """
            SELECT e.*, DENSE_RANK() OVER (PARTITION BY e.course_id ORDER BY e.enrollment_date ASC) as course_rank
            FROM enrollment e
            WHERE e.course_id = :courseId
        """,
        nativeQuery = true
    )
    List<Object[]> rankStudentsInCourse(@Param("courseId") Long courseId);

    // --- Additional complex SQL queries for analytics and allocation ---

    /**
     * Find the top N courses with the highest number of enrollments (using window function RANK).
     */
    @Query(
        value = """
            SELECT course_id, COUNT(*) as enroll_count,
                   RANK() OVER (ORDER BY COUNT(*) DESC) as rank
            FROM enrollment
            GROUP BY course_id
            ORDER BY enroll_count DESC
            LIMIT :topN
        """,
        nativeQuery = true
    )
    List<Object[]> findTopNCoursesByEnrollment(@Param("topN") int topN);

    /**
     * Retrieve students who have enrolled in every available course (relational division).
     */
    @Query(
        value = """
            SELECT user_id
            FROM enrollment
            GROUP BY user_id
            HAVING COUNT(DISTINCT course_id) = (SELECT COUNT(*) FROM course)
        """,
        nativeQuery = true
    )
    List<Long> findStudentsEnrolledInAllCourses();

    /**
     * Find students who registered for the same course multiple times (data anomaly detection).
     */
    @Query(
        value = """
            SELECT user_id, course_id, COUNT(*) as times_enrolled
            FROM enrollment
            GROUP BY user_id, course_id
            HAVING COUNT(*) > 1
        """,
        nativeQuery = true
    )
    List<Object[]> findDuplicateEnrollments();

    /**
     * For each student, get the course they enrolled in earliest using window function.
     */
    @Query(
        value = """
            SELECT *
            FROM (
                SELECT e.*, ROW_NUMBER() OVER (PARTITION BY e.user_id ORDER BY e.enrollment_date ASC) as rn
                FROM enrollment e
            ) ranked
            WHERE rn = 1
        """,
        nativeQuery = true
    )
    List<Enrollment> findEarliestEnrollmentPerStudent();

    /**
     * Get courses with zero enrollments (left join with course).
     */
    @Query(
        value = """
            SELECT c.*
            FROM course c
            LEFT JOIN enrollment e ON c.id = e.course_id
            WHERE e.id IS NULL
        """,
        nativeQuery = true
    )
    List<Object[]> findCoursesWithNoEnrollments();

    /**
     * For each course, calculate the percentage of students enrolled out of all students.
     * (Assumes there is a 'users' table and all students are in it.)
     */
    @Query(
        value = """
            SELECT c.id as course_id,
                   COUNT(e.id) as enroll_count,
                   (100.0 * COUNT(e.id) / (SELECT COUNT(*) FROM users)) as percent_enrolled
            FROM course c
            LEFT JOIN enrollment e ON c.id = e.course_id
            GROUP BY c.id
        """,
        nativeQuery = true
    )
    List<Object[]> findCourseEnrollmentPercentages();

    /**
     * List students who enrolled in a course after a certain date (parameterized).
     */
    @Query(
        value = """
            SELECT e.*
            FROM enrollment e
            WHERE e.enrollment_date > :dateAfter
        """,
        nativeQuery = true
    )
    List<Enrollment> findEnrollmentsAfterDate(@Param("dateAfter") java.time.LocalDateTime dateAfter);
}
