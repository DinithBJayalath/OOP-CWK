function ConfigForm() {
  return (
    <div className="config-form">
        <h1>Configuration</h1>
        <form>
            <label htmlFor="total-tickets" className="total-tickets">Total ticket count:</label>
            <input type="number" id="total-tickets" name="total-tickets" min="1" required />
            <label htmlFor="ticket-release" className="ticket-release">Ticket release rate:</label>
            <input type="number" id="ticket-release" name="ticket-release" min="1" required />
            <label htmlFor="customer-retrieval" className="customer-retrieval">Customer retrieval rate:</label>
            <input type="number" id="customer-retrieval" name="customer-retrieval" min="1" required />
            <label htmlFor="max-ticket" className="max-ticket">Max ticket count:</label>
            <input type="number" id="max-ticket" name="max-ticket" min="1" required />
            <button type="submit">Start</button>
        </form>
    </div>
  )
}

export default ConfigForm