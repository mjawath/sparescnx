interface IIncident {
    id: string
    description: string
    status: boolean
    createdAt?: string
    updatedAt?: string
}

type IncidentProps = {
    incident: IIncident
}

type ApiDataType = {
    message: string
    status: string
    incidents: IIncident[]
    incident?: IIncident
  }
  