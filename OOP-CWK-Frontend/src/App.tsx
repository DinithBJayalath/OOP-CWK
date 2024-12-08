import ConfigForm from "./components/ConfigForm";
import LogDisplay from "./components/LogDisplay";
import TicketDetails from "./components/ticketDetails";

function App() {
  return (
    <div className="App">
      <ConfigForm />
      <TicketDetails />
      <LogDisplay />
    </div>
  )
}

export default App