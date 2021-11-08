import moment from "moment"
import 'moment-duration-format'

export function secToDuration(seconds) {
    return moment.duration(seconds, 'seconds').format('hh:mm:ss', { trim: false })
}

export default { secToDuration }