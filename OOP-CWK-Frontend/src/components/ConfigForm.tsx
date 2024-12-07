import { SubmitHandler ,useForm} from "react-hook-form";
import './ConfigForm.css';

type FormValues = {
    totalTickets: number;
    ticketRelease: number;
    customerRetrieval: number;
    maxTickets: number;
};

function ConfigForm() {
  const { register, handleSubmit } = useForm<FormValues>();
  const onSubmit: SubmitHandler<FormValues> = (data) => {
    console.log(data);
  }
  return (
    <div className="config-form">
        <h3>Configuration</h3>
        <form className="form-config-form" onSubmit={handleSubmit(onSubmit)}>
            <input {...register("totalTickets")} className="form-control" placeholder="Enter total ticket number" type="number" id="total-tickets" min="1" />
            <input {...register("ticketRelease")} className="form-control" placeholder="Enter ticket release rate" type="number" id="ticket-release" min="1" />
            <input {...register("customerRetrieval")} className="form-control" placeholder="Enter Customer retrieval rate" type="number" id="customer-retrieval" min="1" />
            <input {...register("maxTickets")} className="form-control" placeholder="Enter max ticket number" type="number" id="max-ticket"  min="1" />
            <button className="btn btn-primary" type="submit">Start</button>
        </form>
    </div>
  )
}

export default ConfigForm