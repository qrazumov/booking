<template>
  <v-card
      height="400"
      width="256"
      class="mx-auto"
  >
    <v-navigation-drawer permanent>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="text-h6">
            qrazumov
          </v-list-item-title>
          <v-list-item-subtitle>
            Бронирование мероприятий
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list
          dense
          nav
      >
        <v-list-item
            v-for="item in items"
            :key="item.title"
            link
            :to="item.to"
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </v-card>
</template>

<script>
export default {
  name: "Sidebar",
  data () {
    return {
      items: [
        { title: 'Главная', icon: 'mdi-view-dashboard', to: '/' },
        { title: 'Календарь', icon: 'mdi-calendar-clock' , to: '/booking'},
      ],
      right: null,
    }
  },
}
</script>

<style scoped>

</style>