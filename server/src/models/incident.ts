import { IIncident } from '../types/incident';
import { model, Schema } from 'mongoose'

const incidentSchema: Schema = new Schema({
    name: {
        type: String,
        required: true
    },
    raisedBy: {
        type: String,
        required: true
    },
    assignedTo: {
        type: String,
        required: true
    },
    description: {
        type: String,
        required: true
    },
    status: {
        type: Boolean,
        required: true
    }
}, { timestamps: true })


export default model<IIncident>('Incident', incidentSchema)