async function apiCall(
    route,
    method, 
    headers = { 
        Accept: 'application/json',
        Authorization: JSON.parse(localStorage.token).access_token,
        'Content-Type': 'application/json'
    },
    body)
{
    try {
        const res = await fetch(`http://localhost:4000${route}`, {
            method: method,
            headers: headers,
            body: method === 'GET' ? '' : body
        })

        if (!res.ok)
            throw new Error(res.status)

        return res
    } catch (error) {
        console.log(error)
        return error
    }
}

export default { apiCall }