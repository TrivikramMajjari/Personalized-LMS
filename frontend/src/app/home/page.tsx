'use client';

import { useState } from "react";

const categories = ["Personalized Learning", "Paths", "Courses"];
const dummyQuestions = [
  { q: "What is your current skill level?", options: ["Beginner", "Intermediate", "Advanced"] },
  { q: "Which area interests you most?", options: ["AI", "DevOps", "Python"] }
];

export default function Home() {
  const [activeTab, setActiveTab] = useState(0);
  const [answers, setAnswers] = useState<string[]>([]);
  const [step, setStep] = useState(0);
  const [courses, setCourses] = useState<any[]>([]);
  const [loading, setLoading] = useState(false);

  // Handle personalized learning Q&A
  const handleAnswer = (option: string) => {
    const newAnswers = [...answers, option];
    setAnswers(newAnswers);
    if (step < dummyQuestions.length - 1) {
      setStep(step + 1);
    } else {
      // Fetch personalized courses from backend
      setLoading(true);
      fetch(`http://localhost:8080/api/courses/personalized?level=${newAnswers[0].toLowerCase()}`)
        .then(res => res.json())
        .then(data => {
          setCourses(data);
          setLoading(false);
        });
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 flex flex-col items-center pt-12">
      <h1 className="text-3xl font-bold mb-8 text-blue-700">Welcome to LMS</h1>
      <div className="bg-white rounded-lg shadow-lg w-full max-w-3xl p-8">
        <div className="flex border-b mb-6">
          {categories.map((cat, idx) => (
            <button
              key={cat}
              className={`px-6 py-2 font-semibold ${
                idx === activeTab
                  ? "border-b-2 border-blue-600 text-blue-600"
                  : "text-gray-500"
              }`}
              onClick={() => { setActiveTab(idx); setStep(0); setAnswers([]); setCourses([]); }}
            >
              {cat}
            </button>
          ))}
        </div>
        {/* Personalized Learning Tab */}
        {activeTab === 0 && (
          <div>
            {courses.length === 0 && step < dummyQuestions.length && (
              <div>
                <p className="mb-4 text-lg font-semibold">{dummyQuestions[step].q}</p>
                <div className="flex gap-4">
                  {dummyQuestions[step].options.map(opt => (
                    <button
                      key={opt}
                      className="px-4 py-2 bg-blue-100 rounded hover:bg-blue-300"
                      onClick={() => handleAnswer(opt)}
                    >
                      {opt}
                    </button>
                  ))}
                </div>
              </div>
            )}
            {loading && <p>Loading personalized courses...</p>}
            {courses.length > 0 && (
              <div>
                <h2 className="text-xl font-bold mb-4">Recommended Courses</h2>
                <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                  {courses.map(course => (
                    <div key={course.id} className="border rounded p-4 bg-gray-50">
                      <h3 className="font-semibold text-blue-700">{course.title}</h3>
                      <p className="text-sm text-gray-500 mb-2">Level: {course.level}</p>
                      <div>
                        <h4 className="font-semibold mb-2">Modules:</h4>
                        {course.modules.map((mod: any, idx: number) => (
                          <div key={idx} className="mb-2">
                            <div className="font-medium">{mod.title}</div>
                            {mod.youtube && (
                              <iframe
                                className="mt-1"
                                width="100%"
                                height="180"
                                src={mod.youtube}
                                title={mod.title}
                                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                                allowFullScreen
                              ></iframe>
                            )}
                          </div>
                        ))}
                      </div>
                      <button className="mt-2 px-4 py-1 bg-green-500 text-white rounded hover:bg-green-600">
                        Enroll & Join Community
                      </button>
                    </div>
                  ))}
                </div>
              </div>
            )}
          </div>
        )}
        {/* Paths Tab */}
        {activeTab === 1 && (
          <div>
            <h2 className="text-xl font-bold mb-4">Learning Paths</h2>
            <ul className="list-disc pl-6">
              <li>Fullstack Java Developer</li>
              <li>DevOps Engineer</li>
              <li>Cloud Architect</li>
              <li>AI Specialist</li>
            </ul>
          </div>
        )}
        {/* Courses Tab */}
        {activeTab === 2 && (
          <div>
            <h2 className="text-xl font-bold mb-4">All Courses</h2>
            <ul className="list-disc pl-6">
              <li>AI for Beginners</li>
              <li>DevOps Essentials</li>
              <li>Python Programming</li>
              <li>Cloud Fundamentals</li>
            </ul>
          </div>
        )}
      </div>
    </div>
  );
}