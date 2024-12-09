import { createContext, useContext, useState } from "react";

interface SubmissionContextType {
  submission: boolean;
  setSubmission: (submission: boolean) => void;
}

interface SubmissionProviderProps {
    children: React.ReactNode;
}

const SubmissionContext = createContext<SubmissionContextType|undefined>(undefined);

export const SubmissionProvider: React.FC<SubmissionProviderProps> = ({children}) => {
  const [submission, setSubmission] = useState(false);
  return (
    <SubmissionContext.Provider value={{submission, setSubmission}}>
      {children}
    </SubmissionContext.Provider>
  );
}

export const useSubmission = () => {
  const context = useContext(SubmissionContext);
  if (context === undefined) {
    throw new Error("useSubmission must be used within a SubmissionProvider");
  }
  return context;
}

export default SubmissionContext;