const state = {
  alertDescription: "",
}

const mutations = {
  setAlertDescription (state, alertDescription) {
    state.alertDescription = alertDescription
  },
}

const actions = {
  setAlertDescription ({ commit }, alertDescription) {
    commit('setAlertDescription', alertDescription)
  },
}

const getters = {
  alertDescription: state => {
    return state.alertDescription
  },
}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
