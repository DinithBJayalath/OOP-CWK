import { SubmitHandler, useForm } from "react-hook-form";
import { useEffect } from "react";

import { useSubmission } from "./SubmissionContext";
import './ConfigForm.css';

const API_URL = "http://localhost:8090/api/v1/config";
const STOP_URL = "http://localhost:8090/api/v1/stop";

type FormValues = {
    totalTickets: number;
    ticketReleaseRate: number;
    customerRetrievalRate: number;
    maxTicketCapacity: number;
};

function ConfigForm() {
  const { register, handleSubmit, formState: {errors}, getValues, reset } = useForm<FormValues>();
  const { setSubmission } = useSubmission();
  useEffect(() => {
    const handleBeforeUnload = async () => {
      await fetch(STOP_URL);
    }
    window.addEventListener("beforeunload", handleBeforeUnload);
    return () => window.removeEventListener("beforeunload", handleBeforeUnload);
  }, []);
  
  const onSubmit: SubmitHandler<FormValues> = async (data) => {
    console.log(data);
    try {
      const response = await fetch(API_URL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
      });
      const result = await response.text();
      console.log(result);
      setSubmission(true);
      reset();
    } catch (error) {
      console.log("Failed to send config data: " + error);
    }
  };

  const validateTicketReleaseRate = (value: number, formValues: FormValues) => {
    if (value <= 0) {
      return "Ticket release rate must be greater than 0";
    } else if (value >= Number(formValues.totalTickets)) {
      return "Ticket release rate must be less than total tickets";
    }
    return undefined;
  }

  const validateCustomerRetrievalRateRate = (value: number, formValues: FormValues) => {
    if (value <= 0) {
      return "Customer retrieval rate must be greater than 0";
    }else if (value >= Number(formValues.totalTickets)) {
      return "Customer retrieval rate must be less than total tickets";
    }
    return undefined;
  }

  const validateMaxTicketCapacity = (value: number, formValues: FormValues) => {
    if (value <= 0) {
      return "Max tickets must be greater than 0";
    } else if (value <= Number(formValues.totalTickets)) {
      return "Max tickets must be greater than total tickets";
    }
    return undefined;
  }
  
  return (
    <div className="config-form">
        <h3>Configuration</h3>
        <form className="form-config-form" onSubmit={handleSubmit(onSubmit)}>
            <input 
              {...register("totalTickets", {
                required: "Total tickets is required",
                validate: (value) => (value > 0 || "Total tickets must be greater than 0")
              })} 
                className="form-control" 
                placeholder="Enter total ticket number" 
                type="number" 
                id="total-tickets" 
                min="1" />
            {errors.totalTickets && 
              <div className="text-danger">{errors.totalTickets.message}</div>
            }
            <input 
              {...register("ticketReleaseRate", 
                {
                  required: "Ticket release rate is required",
                  validate: (value) => validateTicketReleaseRate(value, getValues())
              })} 
              className="form-control" 
              placeholder="Enter ticket release rate" 
              type="number" 
              id="ticket-release" 
              min="1" />
            {errors.ticketReleaseRate && 
              <div className="text-danger">{errors.ticketReleaseRate.message}</div>
            }
            <input 
              {...register("customerRetrievalRate", 
                {
                  required: "Customer retrieval rate is required",
                  validate: (value) => validateCustomerRetrievalRateRate(value, getValues())
              })} 
              className="form-control" 
              placeholder="Enter Customer retrieval rate" 
              type="number" 
              id="customer-retrieval" 
              min="1" />
            {errors.customerRetrievalRate && 
              <div className="text-danger">{errors.customerRetrievalRate.message}</div>
            }
            <input 
              {...register("maxTicketCapacity", 
                {
                  required: "Max tickets is required",
                  validate: (value) => validateMaxTicketCapacity(value, getValues())
              })} 
              className="form-control" 
              placeholder="Enter max ticket number" 
              type="number" 
              id="max-ticket"  
              min="1" />
            {errors.maxTicketCapacity && 
              <div className="text-danger">{errors.maxTicketCapacity.message}</div>
            }
            <button className="btn btn-primary" type="submit">Start</button>
        </form>
    </div>
  )
}

export default ConfigForm