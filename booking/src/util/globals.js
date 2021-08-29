const EN_LOCALE_ONLY = process.env.EN_LOCALE_ONLY === 'true'
const IN_BROWSER = typeof window !== 'undefined'
const IS_DEBUG = process.env.DEBUG === 'true'
const IS_PROD = process.env.NODE_ENV === 'production'
let API_SERVER

if (IS_PROD) {
  API_SERVER = '/api'
} else {
  API_SERVER = 'http://localhost:8080/api'
}

const API_SERVER_SIGNIN = API_SERVER + '/auth/signin'
const API_SERVER_SIGNUP = API_SERVER + '/auth/signup'

const BOOKING = 'booking'
const API_SERVER_BOOKING = API_SERVER + '/v1/booking'

module.exports = {
  EN_LOCALE_ONLY,
  IN_BROWSER,
  IS_DEBUG,
  IS_PROD,
  API_SERVER,
  API_SERVER_SIGNIN,
  API_SERVER_SIGNUP,
  BOOKING,
  API_SERVER_BOOKING
}
