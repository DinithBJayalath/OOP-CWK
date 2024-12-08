import { SubmitHandler, useForm } from "react-hook-form";

import './ConfigForm.css';

type FormValues = {
    totalTickets: number;
    ticketRelease: number;
    customerRetrieval: number;
    maxTickets: number;
};

function ConfigForm() {
  const { register, handleSubmit, formState: {errors}, getValues } = useForm<FormValues>();
  
  const onSubmit: SubmitHandler<FormValues> = (data) => {
    console.log(data);
  }

  const validateTicketRelease = (value: number, formValues: FormValues) => {
    if (value <= 0) {
      return "Ticket release rate must be greater than 0";
    } else if (value >= Number(formValues.totalTickets)) {
      return "Ticket release rate must be less than total tickets";
    }
    return undefined;
  }

  const validateCustomerRetrieval = (value: number, formValues: FormValues) => {
    if (value <= 0) {
      return "Customer retrieval rate must be greater than 0";
    }else if (value >= Number(formValues.totalTickets)) {
      return "Customer retrieval rate must be less than total tickets";
    }
    return undefined;
  }

  const validateMaxTickets = (value: number, formValues: FormValues) => {
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
              {...register("ticketRelease", 
                {
                  required: "Ticket release rate is required",
                  validate: (value) => validateTicketRelease(value, getValues())
              })} 
              className="form-control" 
              placeholder="Enter ticket release rate" 
              type="number" 
              id="ticket-release" 
              min="1" />
            {errors.ticketRelease && 
              <div className="text-danger">{errors.ticketRelease.message}</div>
            }
            <input 
              {...register("customerRetrieval", 
                {
                  required: "Customer retrieval rate is required",
                  validate: (value) => validateCustomerRetrieval(value, getValues())
              })} 
              className="form-control" 
              placeholder="Enter Customer retrieval rate" 
              type="number" 
              id="customer-retrieval" 
              min="1" />
            {errors.customerRetrieval && 
              <div className="text-danger">{errors.customerRetrieval.message}</div>
            }
            <input 
              {...register("maxTickets", 
                {
                  required: "Max tickets is required",
                  validate: (value) => validateMaxTickets(value, getValues())
              })} 
              className="form-control" 
              placeholder="Enter max ticket number" 
              type="number" 
              id="max-ticket"  
              min="1" />
            {errors.maxTickets && 
              <div className="text-danger">{errors.maxTickets.message}</div>
            }
            <button className="btn btn-primary" type="submit">Start</button>
        </form>
    </div>
  )
}

export default ConfigForm