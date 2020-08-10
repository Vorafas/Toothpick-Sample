package com.example.toothpicksample;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;

public class UserActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText;
    private Button loadButton, saveButton;

    @Inject
    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        initButtons();

        Scope appScope = Toothpick.openScope("APP");
        Toothpick.inject(this, appScope);

        if (savedInstanceState == null) {
            loadUser();
        }
    }

    private void bindViews() {
        nameEditText = findViewById(R.id.edit_text_name);
        emailEditText = findViewById(R.id.edit_text_email);

        loadButton = findViewById(R.id.button_load);
        saveButton = findViewById(R.id.button_save);
    }

    private void initButtons() {
        loadButton.setOnClickListener(v -> loadUser());
        saveButton.setOnClickListener(v -> saveUser());
    }

    private void loadUser() {
        showUser(userRepository.getUser());
    }

    private void showUser(User user) {
        fillEditText(nameEditText, user.getName());
        fillEditText(emailEditText, user.getEmail());
    }

    private void fillEditText(EditText editText, String text) {
        editText.setText(text);
        editText.setSelection(text.length());
    }

    private void saveUser() {
        User user = new User(
                nameEditText.getText().toString().trim(),
                emailEditText.getText().toString().trim()
        );
        userRepository.saveUser(user);
    }


}
