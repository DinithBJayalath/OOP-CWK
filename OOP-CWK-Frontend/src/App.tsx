import ConfigForm from "./components/ConfigForm";
import DynamicAdd from "./components/DynamicAdd";
import LogDisplay from "./components/LogDisplay";
import { SubmissionProvider } from "./components/SubmissionContext";
import TicketDetails from "./components/TicketDetails";

function App() {
  return (
    <div className="App">
      <SubmissionProvider>
        <ConfigForm />
        <TicketDetails />
        <LogDisplay />
        <DynamicAdd />
      </SubmissionProvider>
    </div>
  )
}

export default App