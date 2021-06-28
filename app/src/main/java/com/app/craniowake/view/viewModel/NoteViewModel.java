package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.app.craniowake.data.model.Complication;
import com.app.craniowake.data.model.Note;
import com.app.craniowake.data.repositories.ComplicationRepository;
import com.app.craniowake.data.repositories.NoteRepository;

import lombok.Getter;

public class NoteViewModel extends WithOperationIdViewModel {
    private final NoteRepository noteRepository;

    @Getter
    private MutableLiveData<String> text;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteRepository = new NoteRepository(application);

        text = new MutableLiveData<>("");
    }

    public void addNote(Note note) {
        noteRepository.insert(note);
    }

    public void addNote(Long operationId, String activityName) {
        addNote(new Note(text.getValue(), activityName, operationId));
    }

    public void addNote(String activityName) {
        addNote(new Note(text.getValue(), activityName, this.operationId));
    }
}
