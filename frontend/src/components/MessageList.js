import React, {Component} from 'react'
import Message from "./Message";
import '../style.css'

const tempData = [
    {
        senderId: 'one',
        text: 'Hello everyone'
    },
    {
        senderId: 'two',
        text: 'Hello'
    },
    {
        senderId: 'three',
        text: 'Well, hi'
    },
]

class MessageList extends Component {
    render() {
        return (
            <div className="message-list">
                {/*{this.props.messages.map((message, index) => {*/}
                 {tempData.map((message, index) => {
                    return (
                        <Message key={index} username={message.senderId} text={message.text}/>
                    )
                })}
            </div>
        )
    }
}

export default MessageList