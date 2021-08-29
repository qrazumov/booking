<template>
  <section />
</template>
<script>

  export default {
    name: 'LogOut',
    mounted () {
      this.$store.dispatch('auth/logout')
      this.$router.push('/')
    },

  }
</script>
