import { Document } from 'mongoose'

export interface IIncident extends Document {
    name: string
    description: string
    status: boolean
}