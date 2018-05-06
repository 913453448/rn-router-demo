import React from 'react';
import {
    View,
    Text,
    StyleSheet,
    Button,
    NativeModules,
    DeviceEventEmitter
} from 'react-native';
import {Actions} from 'react-native-router-flux';
import {MessageBarManager} from 'react-native-message-bar';

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: 'transparent',
    },
});

class Launch extends React.Component {
    // 构造
    constructor(props) {
        super(props);
        DeviceEventEmitter.addListener('ACTION_PAGE',()=>{
            //跳转一个新页面
            Actions.launch();
        })
    }

    render() {
        return (
            <View {...this.props} style={styles.container}>
                <Text>Welcome</Text>
                <Button title="Go to Login"
                        onPress={() => Actions.login({data: 'Custom data', title: 'Custom title'})}/>
                <Button title="Go to Register page" onPress={Actions.register}/>
                <Button title="Display Error Modal" onPress={Actions.error}/>
                <Button title="Display Lightbox" onPress={Actions.demo_lightbox}/>
                <Button title="Go to CustomNavBar page" onPress={() => Actions.customNavBar()}/>
                <Button
                    title="MessageBar alert"
                    onPress={() => MessageBarManager.showAlert({
                        title: 'Your alert title goes here',
                        message: 'Your alert message goes here',
                        alertType: 'success',
                        // See Properties section for full customization
                        // Or check `index.ios.js` or `index.android.js` for a complete example
                    })}
                />
                <Button title="Go to TabBar page" onPress={Actions.drawer}/>
                <Button title="跳转到原生的页面" onPress={() => {
                    //调用原生的startNative开启一个原生页面
                    NativeModules.RouterModule.startNative({title: '原生页面'});
                }}/>
                <Button title="Back" onPress={() => {
                    //rn返回的时候调用原生的reactBack方法
                    NativeModules.RouterModule.reactBack();
                    Actions.pop();
                }}/>
            </View>
        );
    }
}

export default Launch;
