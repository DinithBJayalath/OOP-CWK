import { useEffect, useState } from "react"

import "./DynamicAdd.css"

const API_URL = "http://localhost:8090/api/v1/updateArrays";

function DynamicAdd() {
  const [vendors, setVendors] = useState(5);
  const [customers, setCustomers] = useState(5);
  const addVendor = () => {
    setVendors(vendors + 1);
  }
  const removeVendor = () => {
    setVendors(vendors - 1);
  }
  const addCustomer = () => {
    setCustomers(customers + 1);
  }
  const removeCustomer = () => {
    setCustomers(customers - 1);
  }
  useEffect(() => {
    const updateArrays = async () => {
      try {
        const response = await fetch(API_URL, {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({vendors, customers})
        });
        const result = await response.text();
        console.log(result);
      } catch (error) {
        console.log("Failed to send config data: " + error);
      }
    }
    updateArrays();
  }, [vendors, customers]);
  return (
    <div className="dynamic-add">
      <h3>Dynamic Add</h3>
      <div className="card-container row">
        <div className="card gap-4">
          <h5>Vendors</h5>
          <div className="adder d-flex gap-4">
            <button className="remove btn btn-light" onClick={removeVendor}>-</button>
            <h6 className="no-vendors">{vendors}</h6>
            <button className="add btn btn-light" onClick={addVendor}>+</button>
          </div>
        </div>
        <div className="card gap-4">
          <h5>Customers</h5>
          <div className="adder d-flex gap-4">
            <button className="remove btn btn-light"onClick={removeCustomer}>-</button>
            <h6 className="no-vendors">{customers}</h6>
            <button className="add btn btn-light" onClick={addCustomer}>+</button>
          </div>
        </div>
      </div>
    </div>
  )
}

export default DynamicAdd