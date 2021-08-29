<template>
  <div>
    <v-alert
        v-model="alert"
        border="left"
        close-text="Close Alert"
        color="orange darken-3"
        dark
    >
     {{description}}
    </v-alert>
  </div>
</template>

<script>
export default {
  name: "Alert",
  data () {
    return {
      alert: true,
    }
  },
  methods: {
  },
  computed: {
    description () {
      return this.$store.getters["booking/alertDescription"]
    },


  },

}
</script>

<style scoped>

</style>