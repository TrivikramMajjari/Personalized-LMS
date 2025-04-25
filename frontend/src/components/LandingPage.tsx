"use client";

import { Button } from "@/components/ui/button";
import { useRouter } from 'next/navigation';

const LandingPage = () => {
  const router = useRouter();

  return (
    <div className="flex flex-col items-center justify-center h-screen bg-secondary">
      <h1 className="text-4xl font-bold mb-4">Welcome to SkillBuilder</h1>
      <p className="text-muted-foreground mb-8">Empowering Your Learning Journey</p>
      <div className="flex space-x-4">
        <Button onClick={() => router.push('/login')}>Login</Button>
        <Button variant="outline" onClick={() => router.push('/signup')}>Sign Up</Button>
      </div>
    </div>
  );
};

export default LandingPage;
