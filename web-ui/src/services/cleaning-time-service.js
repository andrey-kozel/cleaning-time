import axios from '../utils/api'

export default class CleaningTimeService {

    loginUser(credentials) {
        return axios.post('auth', credentials)
    }

}