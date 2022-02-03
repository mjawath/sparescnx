import React, { useEffect, useState } from 'react'
import IncidentItem from './components/IncidentItem'
import AddIncident from './components/AddIncident'
import { getIncidents, addIncident, updateIncident, deleteIncident } from './API'

const App: React.FC = () => {
  const [incidents, setIncidents] = useState<IIncident[]>([])

  useEffect(() => {
    fetchIncidents()
  }, [])

  const fetchIncidents = (): void => {
    getIncidents()
    .then(response => setIncidents(response.data))
    .catch((err: Error) => console.log(err))
  }

 const handleSaveIncident = (e: React.FormEvent, formData: IIncident): void => {
   e.preventDefault()
   addIncident(formData)
   .then(( data ) => {
    console.log(data)
    // setIncidents(data)
  })
  .catch((err) => console.log(err))
}

  const handleUpdateIncident = (incident: IIncident): void => {
    updateIncident(incident)
    .then(({ status, data }) => {
        if (status !== 200) {
          throw new Error('Error! Incident not updated')
        }
        setIncidents(data.incidents)
      })
      .catch((err) => console.log(err))
  }

  const handleDeleteIncident = (id: string): void => {
    deleteIncident(id)
    .then(({ status, data }) => {
        if (status !== 200) {
          throw new Error('Error! Incident not deleted')
        }
        setIncidents(data.incidents)
      })
      .catch((err) => console.log(err))
  }

  return (
    <main className='App'>
      <h1>My Incidents</h1>
      <AddIncident saveIncident={handleSaveIncident} />
      {incidents.map((incident: IIncident) => (
        <IncidentItem
          key={incident.id}
          updateIncident={handleUpdateIncident}
          deleteIncident={handleDeleteIncident}
          incident={incident}
        />
      ))}
    </main>
  )
}

export default App
