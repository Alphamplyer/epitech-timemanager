export async function createUser(props) {
    const contentType = 'application/json'

    try {
        console.log('props:', props)
        const res = await fetch('http://localhost:4000/api/users', {
            method: 'POST',
            headers: {
                Accept: contentType,
                'Content-Type': contentType
            },
            body: JSON.stringify({
                email: props.email,
                username: props.username,
                password: props.password
            })
        })

        console.log("res apres:", res)

        if (!res.ok)
            throw new Error("Error when creating the user")
        
        return res
    } catch (error) {
        console.log('erreur du catch:',error)
        return error        
    }
}

// export async function getUser(props) {
//     try {
        
//     } catch (error) {
//         console.log('Error:', error)        
//     }
// }

// export async function deleteUser(props) {
//     try {
        
//     } catch (error) {
//         console.log('Error:', error)        
//     }
// }

// export async function getUsers() {
//     try {
        
//     } catch (error) {
//         console.log('Error:', error)        
//     }
// }