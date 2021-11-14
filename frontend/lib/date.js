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

    allDates.map(date => {
        if (moment().isSame(date.start, 'day')) {
            duration = addSecondsToDuration(duration, dateDiff(date.start, date.end))
        }
    })

    return duration
}

export function dateOfSameMoment(dates, time, now = moment()) {
    return dates.filter(date => now.isSame(date, time))
}

export function dayOfWT(dates) {
    console.log('dates:', dates)
    let save = [0, 0]

    dates.map(date => {
        save[0] += (dateDiff(date.start, date.end) / 3600)
    })

    if (save[0] < 8)
        save[1] = 8 - save[0]

    save.map((number, index) => {
        save[index] = number.toFixed(2)
    })

    return save
}

export function weekOfWT(dates) {
    let save = [0, 0, 0, 0, 0, 0, 0]

    dates.map(date => {
        save[moment(date.start).day() - 1] += (dateDiff(date.start, date.end) / 3600)
    })

    return save
}

export default { 
    secToDuration, 
    addDurationToDate, 
    addSecondsToDuration, 
    computeDurationDiff,
    dateDiff,
    dateOfSameMoment,
    dayOfWT
}