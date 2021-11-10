import moment from "moment"
import 'moment-duration-format'

export function secToDuration(seconds) {
    return moment.duration(seconds, 'seconds').format('hh:mm:ss', { trim: false })
}

export function addDurationToDate(date, duration) {
    const newDate = moment(date).add(duration, 'seconds').format('MM-DD-YYYY HH:mm:ss')

    console.log('newDate:', newDate)

    return newDate
}

export function addSecondsToDuration(savedDuration, seconds) {
    const firstTime = savedDuration.split(':')

    const time = moment().startOf('day')
        .add(parseInt(firstTime[0]), 'hours')
        .add(parseInt(firstTime[1]), 'minutes')
        .add(seconds + parseInt(firstTime[2]), 'seconds')

    return time.format('HH:mm:ss')
}

export default { secToDuration, addDurationToDate, addSecondsToDuration }