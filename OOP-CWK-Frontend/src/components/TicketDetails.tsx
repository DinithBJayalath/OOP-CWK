import { useState, useEffect} from "react"
import { useSubmission } from "./SubmissionContext";

import "./ticketDetails.css"

const API_URL = "http://localhost:8090/api/v1/tickets";
const RESET_URL = "http://localhost:8090/api/v1/reset";

interface TicketData {
  availableTickets: number;
  ticketsAdded: number;
  ticketsSold: number;
}

function TicketDetails() {
  const [ticketData, setTicketData] = useState<TicketData>({availableTickets:0, ticketsAdded:0, ticketsSold:0});
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const {submission} = useSubmission();
  useEffect(() => {
    if (!submission) {
      return;
    }
    const fetchTicketData = async () => {
      setIsLoading(true);
      try {
        const response = await fetch(API_URL);
        const data = await response.json() as TicketData;
        setTicketData(data);
      }catch (error) {
        setError(`Error: ${error}`);
        console.log(error);
      }finally {
        setIsLoading(false);
      }
    };
    // fetchTicketData();
    //TODO: Comment when not polling and uncomment when polling is needed
    const interval = setInterval(fetchTicketData, 2000);
    const handleBeforeUnload = async () => {
      await fetch(RESET_URL, { method: "POST" });
    }
    window.addEventListener("beforeunload", handleBeforeUnload);
    return () => {
      clearInterval(interval);
      window.removeEventListener("beforeunload", handleBeforeUnload);
    }
  }, [submission]);
  return (
    <div className="ticket-details font-weight-bold">
        {error && <div className="error">Failed to load data</div>}
        {!error && 
          <>
            <h3>Ticket details</h3>
            <div className="d-flex gap-4-6">
                <div>Available tickets in the queue</div>
                <div>:</div>
                <div className="ticket-data">{isLoading? "Fetching" : ticketData.availableTickets}</div>
            </div>
            <div className="d-flex gap-1">
                <div>Total tickets added to the queue</div>
                <div>:</div>
                <div className="ticket-data">{isLoading? "Fetching" : ticketData.ticketsAdded}</div>
            </div>
            <div className="d-flex gap-28-5">
                <div>Total tickets sold</div>
                <div>:</div>
                <div className="ticket-data">{isLoading? "Fetching" : ticketData.ticketsSold}</div>
            </div>  
          </>
        }  
    </div>
  )
}

export default TicketDetails