<template>
  <v-row justify="center">
    <v-dialog
        v-model="dialog"
        persistent
        max-width="600px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
            color="success"
            class="my-10"
            dark
            v-bind="attrs"
            v-on="on"
        >
          Добавить событие
        </v-btn>
      </template>
      <v-form  ref="form" lazy-validation>
        <v-card>
          <v-card-title>
            <span class="text-h5">Добавить новое событие</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <Alert v-if="isAlert"></Alert>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                      v-model="title"
                      label="Событие*"
                      :rules="nameRules"
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <p>Начало (дата)*: </p>
                  <date-picker
                      v-model="eventStart"
                      format="YYYY-MM-DD HH:mm"
                      value-type="YYYY-MM-DDTHH:mm"
                      type="datetime"
                  ></date-picker>
                </v-col>
                <v-col cols="12">
                  <p>Конец (дата)*: </p>
                  <date-picker
                      v-model="eventEnd"
                      format="YYYY-MM-DD HH:mm"
                      value-type="YYYY-MM-DDTHH:mm"
                      type="datetime"
                  ></date-picker>
                </v-col>
              </v-row>
            </v-container>
            <small>*поля со звездочкой обязательны к заполнению</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                color="blue darken-1"
                text
                @click="dialog = false"
            >
              Закрыть
            </v-btn>
            <v-btn
                color="blue darken-1"
                text
                @click="addBooking"
            >
              Добавить
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-form>

    </v-dialog>
  </v-row>
</template>

<script>


import BookingService from "@/services/booking.service"
import Booking from "@/models/booking"
import Alert from "@/components/Alert"


export default {
  name: "AddBooking",
  components: {Alert},
  data: () => ({
    eventStart: null,
    eventEnd: null,
    title: null,
    dialog: false,
    isAlert: false,
    nameRules: [
      v => !!v || 'Обязательное поле',
      v => (v && v.length >= 5) || 'Поле должно содержать не мнее 5 символов',
    ],
  }),
  methods: {
    addBooking() {
      if(this.$refs.form.validate()){

        if(this.eventStart == null || this.eventEnd == null){
          const msg = 'Выберите диапазон дат для события'
          this.$store.dispatch('booking/setAlertDescription', msg)
          this.isAlert = true
          return;
        }

        const booking = new Booking(
            this.title,
            this.eventStart,
            this.eventEnd,
        )
        BookingService.addBooking(booking)
            .then(r => {
              this.refresh(r)
              this.dialog = false
              this.isAlert = false
              this.eventStart = null
              this.eventEnd = null
              this.title = null
            })
            .catch(err => {
              this.$store.dispatch('booking/setAlertDescription', err.response.data.message)
              this.isAlert = true
            })
      }
    },
    refresh(r) {
      this.$emit('refresh', r)
    },
  },
}
</script>

<style scoped>

</style>