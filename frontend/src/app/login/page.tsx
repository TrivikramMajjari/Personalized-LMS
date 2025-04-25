'use client';

import { useState } from "react";
import { useRouter } from "next/navigation";

export default function Login() {
  const [form, setForm] = useState({ username: "", password: "" });
  const [message, setMessage] = useState("");
  const router = useRouter();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setMessage("Logging in...");
    try {
      const res = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });
      if (res.ok) {
        setMessage("Login successful! Redirecting...");
        router.push("/home");
      } else {
        setMessage("Invalid username or password");
      }
    } catch (err) {
      setMessage("Network error. Please try again.");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gradient-to-br from-green-100 to-blue-200">
      <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold mb-6 text-center text-green-700">Login</h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            className="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-green-400"
            name="username"
            placeholder="Username"
            value={form.username}
            onChange={handleChange}
            required
          />
          <input
            className="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-green-400"
            name="password"
            placeholder="Password"
            value={form.password}
            onChange={handleChange}
            required
            type="password"
          />
          <button
            type="submit"
            className="w-full py-2 bg-green-600 text-white rounded hover:bg-green-700 transition"
          >
            Login
          </button>
        </form>
        {message && <p className="mt-4 text-center text-sm text-gray-700">{message}</p>}
        <p className="mt-4 text-center text-sm">
          Don't have an account? <a href="/signup" className="text-green-600 underline">Sign Up</a>
        </p>
      </div>
    </div>
  );
}

// 'use client';

// import { useState } from "react";
// import { useRouter } from "next/navigation";

// export default function Login() {
//   const [form, setForm] = useState({ username: "", password: "" });
//   const [message, setMessage] = useState("");
//   const router = useRouter();

//   const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
//     setForm({ ...form, [e.target.name]: e.target.value });
//   };

//   const handleSubmit = async (e: React.FormEvent) => {
//     e.preventDefault();
//     setMessage("Logging in...");
//     const res = await fetch("http://localhost:8080/api/auth/login", {
//       method: "POST",
//       headers: { "Content-Type": "application/json" },
//       body: JSON.stringify(form),
//     });
//     if (res.ok) {
//       setMessage("Login successful! Redirecting...");
//       router.push("/home");
//     } else {
//       setMessage("Invalid username or password");
//     }
//   };

//   return (
//     <div className="flex items-center justify-center min-h-screen bg-gradient-to-br from-green-100 to-blue-200">
//       <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
//         <h2 className="text-2xl font-bold mb-6 text-center text-green-700">Login</h2>
//         <form onSubmit={handleSubmit} className="space-y-4">
//           <input
//             className="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-green-400"
//             name="username"
//             placeholder="Username"
//             value={form.username}
//             onChange={handleChange}
//             required
//           />
//           <input
//             className="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-green-400"
//             name="password"
//             placeholder="Password"
//             value={form.password}
//             onChange={handleChange}
//             required
//             type="password"
//           />
//           <button
//             type="submit"
//             className="w-full py-2 bg-green-600 text-white rounded hover:bg-green-700 transition"
//           >
//             Login
//           </button>
//         </form>
//         {message && <p className="mt-4 text-center text-sm text-gray-700">{message}</p>}
//         <p className="mt-4 text-center text-sm">
//           Don't have an account? <a href="/signup" className="text-green-600 underline">Sign Up</a>
//         </p>
//       </div>
//     </div>
//   );
// }
// // 'use client';

// // import { useState } from "react";
// // import { useRouter } from "next/navigation";

// // export default function Login() {
// //   const [form, setForm] = useState({ username: "", password: "" });
// //   const [message, setMessage] = useState("");
// //   const router = useRouter();

// //   const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
// //     setForm({ ...form, [e.target.name]: e.target.value });
// //   };

// //   const handleSubmit = async (e: React.FormEvent) => {
// //     e.preventDefault();
// //     setMessage("Logging in...");
// //     const res = await fetch("http://localhost:8080/api/auth/login", {
// //       method: "POST",
// //       headers: { "Content-Type": "application/json" },
// //       body: JSON.stringify(form),
// //     });
// //     if (res.ok) {
// //       setMessage("Login successful! Redirecting...");
// //       router.push("/home"); // Immediate redirect
// //     } else {
// //       setMessage("Invalid username or password");
// //     }
// //   };

// //   return (
// //     <div className="flex items-center justify-center min-h-screen bg-gradient-to-br from-green-100 to-blue-200">
// //       <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
// //         <h2 className="text-2xl font-bold mb-6 text-center text-green-700">Login</h2>
// //         <form onSubmit={handleSubmit} className="space-y-4">
// //           <input
// //             className="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-green-400"
// //             name="username"
// //             placeholder="Username"
// //             value={form.username}
// //             onChange={handleChange}
// //             required
// //           />
// //           <input
// //             className="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-green-400"
// //             name="password"
// //             placeholder="Password"
// //             value={form.password}
// //             onChange={handleChange}
// //             required
// //             type="password"
// //           />
// //           <button
// //             type="submit"
// //             className="w-full py-2 bg-green-600 text-white rounded hover:bg-green-700 transition"
// //           >
// //             Login
// //           </button>
// //         </form>
// //         {message && <p className="mt-4 text-center text-sm text-gray-700">{message}</p>}
// //         <p className="mt-4 text-center text-sm">
// //           Don't have an account? <a href="/signup" className="text-green-600 underline">Sign Up</a>
// //         </p>
// //       </div>
// //     </div>
// //   );
// // }
// // // 'use client';

// // // import { useState } from "react";
// // // import { useRouter } from "next/navigation";

// // // export default function Login() {
// // //   const [form, setForm] = useState({ username: "", password: "" });
// // //   const [message, setMessage] = useState("");
// // //   const router = useRouter();

// // //   const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
// // //     setForm({ ...form, [e.target.name]: e.target.value });
// // //   };

// // //   const handleSubmit = async (e: React.FormEvent) => {
// // //     e.preventDefault();
// // //     setMessage("Logging in...");
// // //     const res = await fetch("http://localhost:8080/api/auth/login", {
// // //       method: "POST",
// // //       headers: { "Content-Type": "application/json" },
// // //       body: JSON.stringify(form),
// // //     });
// // //     if (res.ok) {
// // //       setMessage("Login successful! Redirecting...");
// // //       setTimeout(() => router.push("/home"), 1000);
// // //     } else {
// // //       setMessage("Invalid username or password");
// // //     }
// // //   };

// // //   return (
// // //     <div className="flex items-center justify-center min-h-screen bg-gradient-to-br from-green-100 to-blue-200">
// // //       <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
// // //         <h2 className="text-2xl font-bold mb-6 text-center text-green-700">Login</h2>
// // //         <form onSubmit={handleSubmit} className="space-y-4">
// // //           <input
// // //             className="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-green-400"
// // //             name="username"
// // //             placeholder="Username"
// // //             value={form.username}
// // //             onChange={handleChange}
// // //             required
// // //           />
// // //           <input
// // //             className="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-green-400"
// // //             name="password"
// // //             placeholder="Password"
// // //             value={form.password}
// // //             onChange={handleChange}
// // //             required
// // //             type="password"
// // //           />
// // //           <button
// // //             type="submit"
// // //             className="w-full py-2 bg-green-600 text-white rounded hover:bg-green-700 transition"
// // //           >
// // //             Login
// // //           </button>
// // //         </form>
// // //         {message && <p className="mt-4 text-center text-sm text-gray-700">{message}</p>}
// // //         <p className="mt-4 text-center text-sm">
// // //           Don't have an account? <a href="/signup" className="text-green-600 underline">Sign Up</a>
// // //         </p>
// // //       </div>
// // //     </div>
// // //   );
// // // }
// // // // 'use client';

// // // // import { useState } from "react";
// // // // import { useRouter } from "next/navigation";

// // // // export default function Login() {
// // // //   const [form, setForm] = useState({ username: "", password: "" });
// // // //   const [message, setMessage] = useState("");
// // // //   const router = useRouter();

// // // //   const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
// // // //     setForm({ ...form, [e.target.name]: e.target.value });
// // // //   };

// // // //   const handleSubmit = async (e: React.FormEvent) => {
// // // //     e.preventDefault();
// // // //     setMessage("Logging in...");
// // // //     const res = await fetch("http://localhost:8080/api/auth/login", {
// // // //       method: "POST",
// // // //       headers: { "Content-Type": "application/json" },
// // // //       body: JSON.stringify(form),
// // // //     });
// // // //     if (res.ok) {
// // // //       setMessage("Login successful! Redirecting...");
// // // //       setTimeout(() => router.push("/home"), 1000);
// // // //     } else {
// // // //       setMessage("Invalid username or password");
// // // //     }
// // // //   };

// // // //   return (
// // // //     <div className="flex items-center justify-center min-h-screen bg-gradient-to-br from-green-100 to-blue-200">
// // // //       <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
// // // //         <h2 className="text-2xl font-bold mb-6 text-center text-green-700">Login</h2>
// // // //         <form onSubmit={handleSubmit} className="space-y-4">
// // // //           <input
// // // //             className="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-green-400"
// // // //             name="username"
// // // //             placeholder="Username"
// // // //             value={form.username}
// // // //             onChange={handleChange}
// // // //             required
// // // //           />
// // // //           <input
// // // //             className="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-green-400"
// // // //             name="password"
// // // //             placeholder="Password"
// // // //             value={form.password}
// // // //             onChange={handleChange}
// // // //             required
// // // //             type="password"
// // // //           />
// // // //           <button
// // // //             type="submit"
// // // //             className="w-full py-2 bg-green-600 text-white rounded hover:bg-green-700 transition"
// // // //           >
// // // //             Login
// // // //           </button>
// // // //         </form>
// // // //         {message && <p className="mt-4 text-center text-sm text-gray-700">{message}</p>}
// // // //         <p className="mt-4 text-center text-sm">
// // // //           Don't have an account? <a href="/signup" className="text-green-600 underline">Sign Up</a>
// // // //         </p>
// // // //       </div>
// // // //     </div>
// // // //   );
// // // // }