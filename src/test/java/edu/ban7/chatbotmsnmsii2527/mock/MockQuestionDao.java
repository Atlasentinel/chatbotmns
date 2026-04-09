package edu.ban7.chatbotmsnmsii2527.mock;

import edu.ban7.chatbotmsnmsii2527.dao.QuestionDao;
import edu.ban7.chatbotmsnmsii2527.model.AppUser;
import edu.ban7.chatbotmsnmsii2527.model.Question;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MockQuestionDao implements QuestionDao {

    private final List<Question> questions = new ArrayList<>();

    public MockQuestionDao() {
        AppUser admin = new AppUser(1, "a@a.com", "root", "Admin", true);
        AppUser user  = new AppUser(2, "b@b.com", "root", "User",  false);

        Question q1 = new Question();
        q1.setId(1);
        q1.setContent("Question de l'admin");
        q1.setUser(admin);
        questions.add(q1); // ← manquant

        Question q2 = new Question();
        q2.setId(2);
        q2.setContent("Question du user");
        q2.setUser(user);
        questions.add(q2); // ← manquant et q2 n'existe pas du tout
    }

    @Override
    public List<Question> findQuestionBy(Integer userId)
    {
        return questions.stream().filter(q -> q.getUser().getId().equals(userId)).toList();
    }

    @Override
    public List<Question> findAll()
    {
        return questions;
    }

    @Override
    public <S extends Question> S save(S entity)
    {
        questions.add(entity);
        return entity;
    }

    @Override public void flush() {}
    @Override public <S extends Question> S saveAndFlush(S entity) { return null; }
    @Override public <S extends Question> List<S> saveAllAndFlush(Iterable<S> entities) { return List.of(); }
    @Override public void deleteAllInBatch(Iterable<Question> entities) {}
    @Override public void deleteAllByIdInBatch(Iterable<Integer> integers) {}
    @Override public void deleteAllInBatch() {}
    @Override public Question getOne(Integer integer) { return null; }
    @Override public Question getById(Integer integer) { return null; }
    @Override public Question getReferenceById(Integer integer) { return null; }
    @Override public <S extends Question> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
    @Override public <S extends Question> List<S> findAll(Example<S> example) { return List.of(); }
    @Override public <S extends Question> List<S> findAll(Example<S> example, Sort sort) { return List.of(); }
    @Override public <S extends Question> Page<S> findAll(Example<S> example, Pageable pageable) { return null; }
    @Override public <S extends Question> long count(Example<S> example) { return 0; }
    @Override public <S extends Question> boolean exists(Example<S> example) { return false; }
    @Override public <S extends Question, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }
    @Override public <S extends Question> List<S> saveAll(Iterable<S> entities) { return List.of(); }
    @Override public Optional<Question> findById(Integer integer) { return Optional.empty(); }
    @Override public boolean existsById(Integer integer) { return false; }
    @Override public List<Question> findAllById(Iterable<Integer> integers) { return List.of(); }
    @Override public long count() { return 0; }
    @Override public void deleteById(Integer integer) {}
    @Override public void delete(Question entity) {}
    @Override public void deleteAllById(Iterable<? extends Integer> integers) {}
    @Override public void deleteAll(Iterable<? extends Question> entities) {}
    @Override public void deleteAll() {}
    @Override public List<Question> findAll(Sort sort) { return List.of(); }
    @Override public Page<Question> findAll(Pageable pageable) { return null; }

}
