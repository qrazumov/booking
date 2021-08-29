import axios from 'axios'
import authHeader from './auth-header'
import GLOBAL from '../util/globals'

class BookingService {


  getBooking () {
    const url = GLOBAL.API_SERVER_BOOKING
    return axios.get(url, { headers: authHeader() })
  }

  addBooking (data) {
    const url = GLOBAL.API_SERVER_BOOKING
    return axios.post(url, data, { headers: authHeader() })
  }
}

export default new BookingService()
