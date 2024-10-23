<script setup>
import { onMounted, ref } from 'vue'
import { fetchUsers } from '/src/api/api.js'

const users = ref([])
const offset = ref(0)
const limit = ref(5)
const totalPages = ref(0)
const currentPage = ref(0)

const previousButtonClick = async () => {
  if (currentPage.value <= 1) {
    return
  }
  await loadData(-limit.value)
}

const nextButtonClick = async () => {
  if (currentPage.value < totalPages.value) {
    await loadData(limit.value)
  }
}

const loadData = async amount => {
  offset.value += amount

  const response = await fetchUsers(limit.value, offset.value)
  users.value = response
  totalPages.value = response.pages
  currentPage.value = response.currentPage
}

onMounted(() => loadData(0))
</script>

<template>
  <div class="dataTable">
    <table>
      <tbody>
        <tr class="tableHeader">
          <th>Id</th>
          <th>Name</th>
          <th>Address</th>
          <th>Email</th>
          <th>Phone number</th>
        </tr>
        <tr v-for="item in users.users" :key="item.id" class="border-b">
          <td>{{ item.id }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.address }}</td>
          <td>{{ item.email }}</td>
          <td>{{ item.phoneNumber }}</td>
        </tr>
      </tbody>
    </table>
    <div class="pagination">
      <button @click="previousButtonClick">Previous</button>
      <p>{{ users.currentPage }} / {{ users.pages }}</p>
      <button @click="nextButtonClick">Next</button>
    </div>
  </div>
</template>

<style scoped>
.tableHeader {
  text-align: left;
}

.dataTable {
  max-width: fit-content;
  margin-left: auto;
  margin-right: auto;
  width: 100%;
  align-content: flex-start;

  h1 {
    margin-bottom: 30px;
  }
}

.pagination {
  display: flex;
  max-width: fit-content;
  margin-left: auto;
  margin-right: auto;
  font-size: 32px;
  width: 100%;

  button {
    margin-top: 40px;
    height: 10%;
  }
  * {
    padding: 0.6vw;
  }
}

table {
  display: block;
  width: 100%;
}

thead {
  position: sticky;
  top: 0;
}
</style>
