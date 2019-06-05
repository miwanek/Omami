import React, {Component} from 'react'

class RoomList extends Component {
    render() {
        return (
            <div className="room-list">
                <ul>
                    <h3>Your rooms:</h3>
                {this.props.rooms.map(room => {
                    return (
                        <li key = {room.id} className="room">
                            <a /*href = {room}*/># {room}</a>
                        </li>
                    )
                })}
                </ul>
            </div>
        )
    }
}

export default RoomList