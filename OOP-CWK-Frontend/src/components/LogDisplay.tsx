import { useEffect, useState } from "react";

import "./LogDisplay.css"

const API_URL = "http://localhost:8090/api/v1/logs";

function LogDisplay() {
  const [logs, setLogs] = useState<string[]>([]);
  // const [error, setError] = useState<string | null>(null);
  useEffect(() => {
    const fetchLogs = async () => {
      try {
        const response = await fetch(API_URL);
        const data = await response.json();
        // console.log("Response: ", data); // TODO: For debugging, remove later
        setLogs((perviousLogs) => [...perviousLogs, ...data]);
      }catch (error) {
        // setError(`Error: ${error}`);
        console.log(error);
        setLogs((perviousLogs) => [...perviousLogs, "Failed to load logs"]);
      }
    };
    setLogs([]);
    // fetchLogs(); // TODO: Comment when the following code is uncommented
    //TODO: Uncomment the following code to enable polling
    const interval = setInterval(fetchLogs, 5000);
    return () => clearInterval(interval);
  }, []);
  return (
    <div className="log-display">
        <h3>Logs</h3>
        <div className="log-list">
          {logs.map((log, index) => (
            <>
              <div key={index} className={`log ${log.includes("INFO") ? "amber-line": ""}`}>{log}</div>
              {log.includes("INFO") && <br />}
            </>
          ))}
        </div>
    </div>
  )
}

export default LogDisplay