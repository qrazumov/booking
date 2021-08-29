<template>
  <v-form
      lazy-validation
  >
    <v-container>
      <v-row>
        <v-col
            cols="12"
            md="4"
        >
          <v-text-field
              :counter="20"
              v-model="user.username"
              label="Логин"
              required
          ></v-text-field>
          <v-text-field
              v-model="user.password"
              label="Пароль"
              required
              type="Password"
          ></v-text-field>
          <v-btn
              color="success"
              class="mr-4"
              @click="handleLogin"
          >
            Войти
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-form>
</template>

<script>

import User from '../models/user'
import GLOBALS from '../util/globals'

export default {
  name: 'Login',
  data () {
    return {
      user: new User('qrazumov', '', 'qrazumov'),
      loading: false,
      message: '',
    }
  },
  computed: {
    loggedIn () {
      return this.$store.state.auth.status.loggedIn
    },
  },
  created () {
    if (this.loggedIn) {
      this.$router.push(GLOBALS.BOOKING)
    }
  },
  methods: {
    handleLogin () {
      this.loading = true

      if (this.user.username && this.user.password) {
        this.$store.dispatch('auth/login', this.user).then(
            () => {
              this.$router.push(GLOBALS.BOOKING)
            },
            error => {
              this.loading = false
              this.message =
                  (error.response && error.response.data) ||
                  error.message ||
                  error.toString()
            },
        )
      }
      // })
    },
  },
}

</script>

<style scoped>

</style>