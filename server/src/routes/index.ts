import { Router } from 'express'
import { getIncidents, addIncident, updateIncident, deleteIncident } from '../controllers/incidents'
 
const router: Router = Router()

router.get('/incidents', getIncidents)

router.post('/add-incident', addIncident)

router.put('/edit-incident/:id', updateIncident)

router.delete('/delete-incident/:id', deleteIncident)

export default router
