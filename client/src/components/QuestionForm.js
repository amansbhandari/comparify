import React, { useState } from "react";


export default function QuestionForm() {
  // const [id,setId] = useState(0);
  // const [question,setQuestion] = useState("what is your name?");
  // const [answer,setAnswer] = useState("qi");
  const id = 1;
  const question = "what is your name?";  
  const answer = "qi";
 
  return (
   <Question id={id} question={question} answer={answer}/> 
  );
}
const Question = (id,question,answer) => {  
  return (
  <div><h2 className="security-question">
      <label>
        {question}
      
      </label>
    </h2>
    <input
        type="text"
        autoComplete="off" />
    </div>
  )
}
