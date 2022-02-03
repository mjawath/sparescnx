import React, { useState } from 'react'

type Props = {
  saveIncident: (e: React.FormEvent, formData: IIncident | any) => void
}

const AddIncident: React.FC<Props> = ({ saveIncident }) => {
  const [formData, setFormData] = useState<IIncident | {}>()

  const handleForm = (e: React.FormEvent<HTMLInputElement>): void => {
    setFormData({
      ...formData,
      [e.currentTarget.id]: e.currentTarget.value,
    })
  }

  const onChangeType = (e:React.FormEvent<HTMLSelectElement>): void => {
    setFormData({
      ...formData,
      [e.currentTarget.id]: e.currentTarget.value,
    })
  }

  return (
    <form className='Form' onSubmit={(e) => saveIncident(e, formData)}>
      <div>
        <div>
          <label htmlFor='description'>Description</label>
          <input onChange={handleForm} type='text' id='description' />
          <label htmlFor='incidentType'>Incident Type</label>
          <select onChange={onChangeType} id='incidentType'  >
            <option value="select">Select</option>
            <option value="Java">Java</option>
            <option value="C++">C++</option>
          </select>
        </div>
      </div>
      <button disabled={formData === undefined ? true : false} >Add Incident</button>
    </form>
  )
}

export default AddIncident
