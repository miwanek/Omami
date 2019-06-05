import React, {Component} from 'react'
import Room from "./Room"
import axios from "axios";

class RoomList extends Component {
    constructor() {
        super();

        this.state = {

        };

        this.showRooms = this.showRooms.bind(this)
    }

    componentWillMount() {
        this.interval = setInterval(() =>
        {
            this.showRooms();
        }, 2000);
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

    showRooms() {
        var self = this;
        axios.get(`http://localhost:5000/user_rooms?userId=${this.props.userId}`
        ).then(function (response) {
            self.props.loadRooms(response.data)
        }).catch(function (error) {
            console.log(error.message);
        });
    };

    render() {
        return (
            <div className="room-list">
                <ul>
                    <h3>Your rooms:</h3>
                {this.props.rooms.map(room => {
                    return (
                        <li key = {room.id} className="room">
                            <Room roomName = {room} setRoom = {this.props.setRoom}/>
                        </li>
                    )
                })}
                </ul>
            </div>
        )
    }
}

export default RoomList