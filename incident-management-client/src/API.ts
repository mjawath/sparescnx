import axios, { AxiosResponse } from 'axios'

const baseUrl: string = 'http://localhost:8080'

export const getIncidents = async (): Promise<AxiosResponse<IIncident[]>> => {
  try {
    const data: AxiosResponse<IIncident[]> = await axios.get(
      baseUrl + '/incidents/'
    )
    return data
  } catch (error) {
    throw new Error()
  }
}

export const addIncident = async (
  formData: IIncident
): Promise<AxiosResponse<String>> => {
  try {
    const incident: Omit<IIncident, 'id'> = {
      description: formData.description,
      status: false,
    }
    const saveIncident: AxiosResponse<any> = await axios.post(
      baseUrl + '/incidents',
      incident
    )
    return saveIncident
  } catch (error) {
    throw new Error(error)
  }
}

export const updateIncident = async (
  incident: IIncident
): Promise<AxiosResponse<ApiDataType>> => {
  try {
    const incidentUpdate: Pick<IIncident, 'status'> = {
      status: true,
    }
    const updatedIncident: AxiosResponse<ApiDataType> = await axios.put(
      `${baseUrl}/incidents/${incident.id}`,
      incidentUpdate
    )
    return updatedIncident
  } catch (error) {
    throw new Error(error)
  }
}

export const deleteIncident = async (
  id: string
): Promise<AxiosResponse<ApiDataType>> => {
  try {
    const deletedIncident: AxiosResponse<ApiDataType> = await axios.delete(
      `${baseUrl}/incidents/${id}`
    )
    return deletedIncident
  } catch (error) {
    throw new Error(error)
  }
}
