import React, {Component} from 'react'

class Message extends Component {
    render() {
        return (
            <div className="message">
                <div className="message">
                    <div className="message-username"> {this.props.username}</div>
                    <div className="message-text"> {this.props.text} </div>
                </div>
            </div>
        )
    }
}

export default Message