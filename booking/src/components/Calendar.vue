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
          @click:event="showEvent"
          locale="ru"
      ></v-calendar>
      <v-menu
          v-model="selectedOpen"
          :close-on-content-click="false"
          :activator="selectedElement"
          offset-x
      >
        <v-card
            color="grey lighten-4"
            min-width="350px"
            flat
        >
          <v-toolbar
              :color="selectedEvent.color"
              dark
          >
            <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <p v-html="selectedEvent.details"></p>
            <p >Дата начала мероприятия: {{selectedEvent.start}}</p>
            <p>Дата окончания мероприятия: {{selectedEvent.end}}</p>

          </v-card-text>
          <v-card-actions>
            <v-btn
                text
                color="secondary"
                @click="selectedOpen = false"
            >
              Выход
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-menu>
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

    selectedEvent: {},
    selectedElement: null,
    selectedOpen: false,
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
    showEvent ({ nativeEvent, event }) {
      const open = () => {
        this.selectedEvent = event
        this.selectedElement = nativeEvent.target
        console.log(this.selectedElement)
        requestAnimationFrame(() => requestAnimationFrame(() => this.selectedOpen = true))
      }

      if (this.selectedOpen) {
        this.selectedOpen = false
        requestAnimationFrame(() => requestAnimationFrame(() => open()))
      } else {
        open()
      }

      nativeEvent.stopPropagation()
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