export async function createUser(props) {
    // const contentType = 'application/json'

    try {
        console.log('props:', props)
        const res = await fetch('/auth/register', {
            method: 'POST',
            headers: {
                Accept: '*/*',
                'Content-Type': contentType
            },
            body: JSON.stringify({
                email: props.email,
                username: props.username,
                password: props.password
            })
        })

        // if (!res.ok)
        //     throw new Error("Error when creating the user")

    } catch (error) {
        console.log(error)        
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