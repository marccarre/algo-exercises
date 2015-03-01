try:
    from setuptools import setup
except ImportError:
    from distutils.core import setup

config = {
    'description': 'Algorithm Exercises, in Python',
    'author': 'Marc Carré',
    'url': 'https://github.com/marccarre/algo-exercises',
    'download_url': 'https://github.com/marccarre/algo-exercises',
    'author_email': 'carre.marc@gmail.com',
    'version': '0.1',
    'install_requires': comma_separated_dependencies(),
    'packages': ['algo_exercises'],
    'scripts': [],
    'name': 'algo_exercises'
}

def comma_separated_dependencies(exclusions=('#', 'nose', 'unittest2')):
    with open('requirements.txt', 'r') as f:
        return ','.join(dep.strip() for dep in f if len(dep.strip()) > 0 and all(not dep.startswith(e) for e in exclusions))

setup(**config)

