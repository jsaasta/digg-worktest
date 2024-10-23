export const fetchUsers = async (limit, offset) => {
  try {
    const response = await fetch(
      'http://localhost:8080/digg/user?limit=' + limit + '&offset=' + offset,
    )
    return await response.json()
  } catch (err) {
    console.log(err)
  }
}
