use jni::{
    EnvUnowned,
    objects::{JClass, JString},
    errors::ThrowRuntimeExAndDefault,
};

#[unsafe(no_mangle)]
pub extern "system" fn Java_com_webapp_app_NativeEngine_hello<'local>(
    mut unowned_env: EnvUnowned<'local>,
    _class: JClass<'local>,
    name: JString<'local>,
) -> JString<'local> {
    let outcome = unowned_env.with_env(|env| {
        let name: String = name.to_string();

        JString::from_str(
            env,
            format!("Olá from Rust {name}!"),
        )
    });

    outcome.resolve::<ThrowRuntimeExAndDefault>()
}