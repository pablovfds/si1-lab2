package controllers;

import models.repository.RepositorioDeAnuncios;
import models.repository.RepositorioDeInstrumentos;
import models.repository.RepositorioDeEstilos;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

    @Transactional
    public static Result index() {
        return ok(index.render());
    }

    @Transactional
    public static Result criarAnuncio() {
        RepositorioDeEstilos estilos = RepositorioDeEstilos.getInstance();
        RepositorioDeInstrumentos instrumentos = RepositorioDeInstrumentos.getInstance();
        return ok(criarAnuncio.render(estilos.findAll(), instrumentos.findAll()));
    }

    @Transactional
    public static Result listaDeAnuncios() {
        RepositorioDeAnuncios anuncios = RepositorioDeAnuncios.getInstance();
        return ok(listaDeAnuncios.render(anuncios.findAll()));
    }

    private void addAnuncio(){

    }

}

/**
package controllers;

        import java.util.List;

        import models.Autor;
        import models.Livro;
        import models.repository.AutorRepository;
        import models.repository.LivroRepository;
        import play.data.Form;
        import play.db.jpa.Transactional;
        import play.mvc.Controller;
        import play.mvc.Result;

        import com.google.common.collect.Iterables;
**/
/**
 * Controlador Principal do Sistema
 */
/**
public class Application extends Controller {
    static Form<Livro> bookForm = Form.form(Livro.class);
    private static LivroRepository livroRepository = LivroRepository
            .getInstance();
    private static AutorRepository autorRepository = AutorRepository
            .getInstance();
    private static final int FIRST_PAGE = 1;

    // Regex de um inteiro positivo
    public static Result index() {
        return redirect(routes.Application.books(FIRST_PAGE,
                LivroRepository.DEFAULT_RESULTS));
    }

    // Nota��o transactional sempre que o m�todo fizer transa��o com o Banco de
    // Dados.
    @Transactional
    public static Result books(int page, int pageSize) {
        page = page >= FIRST_PAGE ? page : FIRST_PAGE;
        pageSize = pageSize >= FIRST_PAGE ? pageSize
                : LivroRepository.DEFAULT_RESULTS;
        Long entityNumber = livroRepository.countAll();
        // Se a p�gina pedida for maior que o n�mero de entidades
        if (page > (entityNumber / pageSize)) {
            // A �ltima p�gina
            if (entityNumber != 0) {
                page = (int) (Math.ceil(entityNumber
                        / Float.parseFloat(String.valueOf(pageSize))));
            } else {
                page = FIRST_PAGE;
            }
        }
        session("actualPage", String.valueOf(page));
        return ok(views.html.index.render(
                livroRepository.findAll(page, pageSize), bookForm));
    }

    // Nota��o transactional sempre que o m�todo fizer transa��o com o Banco de
    // Dados.
    @Transactional
    public static Result newBook() {
        // O formul�rio dos Livros Preenchidos
        Form<Livro> filledForm = bookForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(views.html.index.render(firstPage(), filledForm));
        } else {
            Livro livro = filledForm.get();
            // Persiste o Livro criado
            livroRepository.persist(livro);
            // Espelha no Banco de Dados
            livroRepository.flush();
            return redirect(routes.Application.books(FIRST_PAGE,
                    LivroRepository.DEFAULT_RESULTS));
        }
    }

    @Transactional
    public static Result addAutor(Long id, String nome) {
        criaAutorDoLivro(id, nome);
        return ok(views.html.index.render(firstPage(), bookForm));
    }

    private static void criaAutorDoLivro(Long id, String nome) {
        Autor autor = autorRepository.findByName(nome);
        // Procura um objeto da classe Livro com o {@code id}
        Livro livroDaListagem = livroRepository.findByEntityId(id);
        // Cria um novo Autor para um livro de {@code id}
        if (autor == null) {
            autor = new Autor();
            autor.setNome(nome);
            // Persiste o Novo Autor
            autorRepository.persist(autor);
        }
        autor.getLivros().add(livroDaListagem);
        livroDaListagem.getAutores().add(autor);
        // Atualiza as informa��es do livro
        livroRepository.merge(livroDaListagem);
        // Espelha no Banco de Dados
        livroRepository.flush();
    }

    // Nota��o transactional sempre que o m�todo fizer transa��o com o Banco de
    // Dados.
    @Transactional
    public static Result deleteBook(Long id) {
        // Remove o Livro pelo Id
        livroRepository.removeById(id);
        // Espelha no banco de dados
        livroRepository.flush();
        return redirect(routes.Application.books(FIRST_PAGE,
                LivroRepository.DEFAULT_RESULTS));
    }

    /**
     * Retorna a primeira p�gina do banco de dados
     */
/**
    private static List<Livro> firstPage() {
        return livroRepository.findAll(FIRST_PAGE,
                LivroRepository.DEFAULT_RESULTS);
    }

}
*/
