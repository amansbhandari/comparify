import React from "react";
import useResources from "./useResources";



const ResourList = ({ resource }) => {
  const resources = useResources(resource);

  return (
    <div>
      <h1>
        {resources.map((record) => (
          <li key={record.id}>{record.question}</li>
        ))}
      </h1>
    </div>
  );
};
export default ResourList;
