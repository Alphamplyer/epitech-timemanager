import moment from "moment"
import 'moment-duration-format'

export function secToDuration(seconds) {
    return moment.duration(seconds, 'seconds').format('HH:mm:ss', { trim: false })
}

export function addDurationToDate(date, duration) {
    const newDate = moment(date).add(duration, 'seconds').format('YYYY-MM-DDTHH:mm:ss')

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

export function dateDiff(startDate, endDate) {
    return moment(startDate).diff(endDate) / - 1000
}

export function computeDurationDiff(allDates) {
    let duration = '00:00:00'

    for (let i = 0; i < allDates.length; i++) {
        if (moment().isSame(allDates[i].start, 'day')) {
            duration = addSecondsToDuration(duration, dateDiff(allDates[i].start, allDates[i].end))
        }
    }

    return duration
}

export default { 
    secToDuration, 
    addDurationToDate, 
    addSecondsToDuration, 
    computeDurationDiff,
    dateDiff
}