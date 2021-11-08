export async function apiCall(
    route,
    method = 'GET', 
    headers = { 
        Accept: 'application/json',
        Authorization: `Bearer ${JSON.parse(localStorage.token).access_token}`,
        'Content-Type': 'application/json'
    },
    body)
{
    try {
        const res = body ? await fetch(`http://localhost:4000${route}`, {
            method: method,
            headers: headers,
            body: body
        }) : await fetch(`http://localhost:4000${route}`, {
            method: method,
            headers: headers })

        if (!res.ok)
            throw new Error(res.status)

        return res
    } catch (error) {
        console.log(error)
        return error
    }
}
