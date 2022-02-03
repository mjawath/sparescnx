import React from 'react'

type Props = IncidentProps & {
    updateIncident: (incident: IIncident) => void
    deleteIncident: (_id: string) => void
}

const Incident: React.FC<Props> = ({ incident, updateIncident, deleteIncident }) => {
  const checkIncident: string = incident.status ? `line-through` : ''
  return (
    <div className='Card'>
      <div className='Card--text'>
        <span className={checkIncident}>{incident.description}</span>
      </div>
      <div className='Card--button'>
        <button
          onClick={() => updateIncident(incident)}
          className={incident.status ? `hide-button` : 'Card--button__done'}
        >
          Complete
        </button>
        <button
          onClick={() => deleteIncident(incident.id)}
          className='Card--button__delete'
        >
          Delete
        </button>
      </div>
    </div>
  )
}

export default Incident
