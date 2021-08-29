<template>
  <div>
    <v-sheet
        tile
        height="54"
        class="d-flex"
    >
      <v-btn
          class="ma-2"
          @click="$refs.calendar.prev()"
      >
        <v-icon>mdi-chevron-left</v-icon>
        пред. неделя
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn
          class="ma-2"
          @click="$refs.calendar.next()"
      >
        след. неделя
        <v-icon>mdi-chevron-right</v-icon>
      </v-btn>
    </v-sheet>
    <v-sheet height="600">
      <v-calendar
          ref="calendar"
          v-model="value"
          :weekdays="weekday"
          :type="type"
          :events="events"
          :event-overlap-mode="mode"
          :event-overlap-threshold="30"
          :event-color="getEventColor"
          locale="ru"
      ></v-calendar>
    </v-sheet>
    <AddBooking
        @refresh="refresh"
    ></AddBooking>
  </div>
</template>

<script>

import BookingService from '../services/booking.service'
import AddBooking from "@/components/AddBooking"


export default {
  name: "Calendar",
  components: {AddBooking},
  data: () => ({
    type: 'week',
    types: ['week', 'month', 'day', '4day'],
    mode: 'stack',
    modes: ['stack', 'column'],
    weekday: [1, 2, 3, 4, 5, 6, 0],
    weekdays: [],
    value: '',
    events: [],
    colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
    booking: [],
  }),
  methods: {
    getEvents() {

      BookingService.getBooking().then(r => {
        this.booking = r.data
        this.events = []
        for (const event in this.booking) {
          this.events.push(
              {
                name: this.booking[event].title,
                details: this.booking[event].title,
                start: this.booking[event].eventStart,
                end: this.booking[event].eventEnd,
                color: this.colors[this.rnd(0, this.colors.length - 1)],
                timed: true
              }
          )
        }

      })

    },
    getEventColor(event) {
      return event.color
    },
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a
    },
    refresh(){
      this.getEvents()
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('signin')
    }
    this.getEvents()
  },

}
</script>

<style scoped>

</style>