<template>
  <v-app>
    <v-navigation-drawer app>
      <Sidebar></Sidebar>
    </v-navigation-drawer>

    <v-app-bar app>
      <v-app-bar-nav-icon></v-app-bar-nav-icon>
      <v-toolbar-title></v-toolbar-title>
      <v-spacer></v-spacer>
      <h1 v-if="currentUser">
        <v-btn to="/logout" color="success">Выход</v-btn>
      </h1>
      <h1 v-else>
        <v-btn to="/signin" color="">Вход</v-btn>
      </h1>

    </v-app-bar>

    <!-- Sizes your content based upon application components -->
    <v-main>

      <!-- Provides the application the proper gutter -->
      <v-container fluid>
        <!-- If using vue-router -->
        <router-view></router-view>

      </v-container>
    </v-main>

    <v-footer app>
      <!-- -->
    </v-footer>
  </v-app>
</template>

<script>
import Sidebar from "@/components/Sidebar";

export default {
  name: 'App',

  components: {
    Sidebar,
  },

  data: () => ({
    //
  }),
  computed: {
    currentUser() {
      console.log(this.$store.state.auth.user)
      return this.$store.state.auth.user
    },
  },
};
</script>
